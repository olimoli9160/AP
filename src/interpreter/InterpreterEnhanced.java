package interpreter;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InterpreterEnhanced {
	private static final char PRINT = '?';
	private static final char COMMENT = '/';
	private static final char SET_START = '{';
	private static final char SET_END = '}';
	private static final char COMPLEX_START = '(';
	private static final char COMPLEX_END = ')';
	private static final char NUMBER_SEPARATOR = ',';
	private static final char EQUAL = '=';
	private static final char UNION = '+';
	private static final char COMPLEMENT = '-';
	private static final char SYM_DIF = '|';
	private static final char INTERSECTION = '*';
	private static final char LESS = '<';
	private static final char GREATER = '>';
	private static final char IF_EXP_START = '%';
	private static final char SEQ_START= '.';
	private static final String IF = "%if";
	private static final String THEN = "then";
	private static final String ELSE = "else";
	private static final String SEQ = "...";

	private HashTable<Identifier, Set<Number>> register;
	private PrintStream out;

	public InterpreterEnhanced() {
		register = new HashTable<Identifier, Set<Number>>();
		out = new PrintStream(System.out);
	}

	private void parser(String inputLine, int lineNumber) throws APException {
		Scanner statementScan = new Scanner(inputLine).useDelimiter("");
		
		statement(statementScan);
		return;
	}

	private void statement(Scanner input) throws APException {
		removeSpaces(input);
		
		if(!input.hasNext()) {
			throw new APException("No Input received");
		} else if(nextCharIs(input, COMMENT)) {
			return;
		} else if(nextCharIs(input, PRINT)) {
			print(input);
		} else if(nextCharIsLetter(input)) {
			assignment(input);
		} else {
			throw new APException("Invalid statement");
		}
	}

	private void assignment(Scanner input) throws APException {
		Identifier newId = identifier(input);
		
		removeSpaces(input);
		character(input, EQUAL);
		removeSpaces(input);
		Set<Number> newSet = expression(input);
		
		removeSpaces(input);
		eoln(input);
		register.add(newId, newSet);
	}

	private Identifier identifier(Scanner input) throws APException {
		Identifier result = new Identifier();
		
		if(letter(input)) {
			
			do {
				result.add(nextChar(input));
			}
			while(letter(input) || number(input));
		} else {
			throw new APException("Id not alphanumeric");
		}
		
		return result;
	}
	
	private Set<Number> expression(Scanner input) throws APException{
		Set<Number> result;
		if(nextCharIs(input, IF_EXP_START)){
			result = if_expression(input);
		} else {
			result = subExpression(input);
		}
		
		return result;
	}

	private Set<Number> subExpression(Scanner input) throws APException {
		Set<Number> result = term(input);
		
		removeSpaces(input);
		
		while(addativeOperator(input)) {
			if(nextCharIs(input, UNION)) {
				character(input, UNION);
				removeSpaces(input);
				result = (Set<Number>)result.union(term(input));
			} else if(nextCharIs(input, COMPLEMENT)) {
				character(input, COMPLEMENT);
				removeSpaces(input);
				result = (Set<Number>)result.complement(term(input));
			} else if(nextCharIs(input, SYM_DIF)) {
				character(input, SYM_DIF);
				removeSpaces(input);
				result = (Set<Number>)result.symmetricDifference(term(input));
			}
		}
		
		return result;
	}
	
	private Set<Number> if_expression(Scanner input) throws APException{
		Set<Number> resultOption1;
		Set<Number> resultOption2;
		boolean booleanResult;
		
		string(input, IF);
		removeSpaces(input);
		booleanResult = boolean_expression(input);
		
		removeSpaces(input);
		string(input, THEN);
		removeSpaces(input);
		
		resultOption1 = complexFactor(input);
		
		removeSpaces(input);
		string(input, ELSE);
		removeSpaces(input);
		
		resultOption2 = complexFactor(input);
		
		if(booleanResult){
			return resultOption1;
		}
		
		return resultOption2;
	}
	
	private boolean boolean_expression(Scanner input) throws APException{
		boolean result = false;
		Set<Number> set1;
		character(input, COMPLEX_START);
		removeSpaces(input);
		
		set1 = expression(input);
		removeSpaces(input);
		
		if(booleanOperator(input)){
			if(nextCharIs(input, EQUAL)) {
				character(input, EQUAL);
				removeSpaces(input);
				result = set1.equal(expression(input));
			} else if(nextCharIs(input, LESS)) {
				character(input, LESS);
				removeSpaces(input);
				result = set1.less(expression(input));
			} else if(nextCharIs(input, GREATER)) {
				character(input, GREATER);
				removeSpaces(input);
				result = set1.greater(expression(input));
			}
		} else {
			throw new APException("Missing valid Boolean operator ");
		}
		
		character(input, COMPLEX_END);
		removeSpaces(input);
		return result;
	}

	private Set<Number> term(Scanner input) throws APException {
		Set<Number> result = factor(input);
		
		removeSpaces(input);
		
		while(multiplicativeOperator(input)) {
			character(input, INTERSECTION);
			removeSpaces(input);
			result = (Set<Number>)result.intersection(factor(input));
		}
		
		return result;
	}

	private Set<Number> factor(Scanner input) throws APException {
		Set<Number> result = new Set<Number>();
		
		if(nextCharIsLetter(input)) {
			Identifier factorId = identifier(input);
			removeSpaces(input);
			if(!register.isEmpty()) {
				if(register.contains(factorId)) {
					result = register.getValue(factorId);
				} else {
					throw new APException("ID undefined");
				}
			} else {
				throw new APException("Empty register");
			}
		} else if(nextCharIs(input, SET_START)) {
			result = set(input);
			removeSpaces(input);
		} else if(nextCharIs(input, COMPLEX_START)) {
			result = complexFactor(input);
			removeSpaces(input);
		} else {
			throw new APException("Invalid factor");
		}
		
		return result;
	}

	private Set<Number> set(Scanner input) throws APException {
		character(input, SET_START);
		removeSpaces(input);
		Set<Number> result = rowNaturalNumbers(input);
		
		removeSpaces(input);
		character(input, SET_END);
		
		return result;
	}

	private Set<Number> rowNaturalNumbers(Scanner input) throws APException {
		Set<Number> result = new Set<Number>();
		
		if(number(input)) {
			Set<Number> newNumberSeq = naturalNumberTerm(input);
			
			result = (newNumberSeq);
			removeSpaces(input);
			
			while(nextCharIs(input, NUMBER_SEPARATOR)) {
				character(input, NUMBER_SEPARATOR);
				removeSpaces(input);
				newNumberSeq = naturalNumberTerm(input);
				
				newNumberSeq.getSetData().goToFirst();
				for(int i = 0; i < newNumberSeq.size(); i++){
					Number nextNumber = newNumberSeq.getSetData().retrieve();
					if(!result.contains(nextNumber)) {
						result.add(nextNumber);
					}
					newNumberSeq.getSetData().goToNext();
				}
				removeSpaces(input);
			}
		}
		
		return result;
	}

	private Set<Number> naturalNumberTerm(Scanner input) throws APException {
		Set<Number> result = new Set<Number>();
		Number newNumber = naturalNumber(input);
		result.add(newNumber);
		
		removeSpaces(input);
		if(nextCharIs(input, SEQ_START)){
			string(input, SEQ);
			removeSpaces(input);
			
			if(number(input)){
				int start = Integer.parseInt(newNumber.toString());
				int end = Integer.parseInt(naturalNumber(input).toString());
				
				if(start <= end){
					int temp = start+1;
					for(int i = start; i < end; i++){
						newNumber = naturalNumber(new Scanner(Integer.toString(temp)).useDelimiter(""));
						result.add(newNumber);
						temp++;
					}
				} else {
					return result = new Set<Number>();
				}
			} else {
				throw new APException("Invalid Sequence End: " + input.next());
			}
		}
		
		return result;
	}
	
	private Set<Number> complexFactor(Scanner input) throws APException {
		character(input, COMPLEX_START);
		removeSpaces(input);
		Set<Number> result = expression(input);
		
		removeSpaces(input);
		character(input, COMPLEX_END);
		
		return result;
	}

	private Number naturalNumber(Scanner input) throws APException {
		Number result;
		
		if(notZero(input)) {
			result = positiveNumber(input);
		} else if(isZero(input)) {
			result = zero(input); 
		} else {
			throw new APException("Invalid number: "+input.next());
		}
		
		return result;
	}

	private Number positiveNumber(Scanner input) throws APException {
		Number result = new Number();
		
		if(notZero(input)) {
			
			do {
				result.add(nextChar(input));
			}
			while(number(input));
		} else {
			throw new APException("Invalid number");
		}

		return result;
	}

	private Number zero(Scanner input) throws APException {
		Number result = new Number();
		
		if(isZero(input)) {
			result.add(nextChar(input));
			if(nextCharIsDigit(input)){
				throw new APException("Invalid Number: 0" + input.next());
			}
		} else {
			throw new APException("Invalid number");
		}

		return result;
	}

	private boolean number(Scanner input) {
		return (isZero(input) || notZero(input)) ? true : false;
	}

	private boolean isZero(Scanner input) {
		return (nextCharIs(input, '0')) ? true : false;
	}

	private boolean notZero(Scanner input) {
		return (nextCharIsDigit(input) && !isZero(input)) ? true : false;
	}

	private boolean letter(Scanner input) {
		return (nextCharIsLetter(input)) ? true : false;
	}
	
	private boolean booleanOperator(Scanner input) {
		return (nextCharIs(input, EQUAL) || nextCharIs(input, LESS) || nextCharIs(input, GREATER)) ? true : false;
	}

	private boolean addativeOperator(Scanner input) {
		return (nextCharIs(input, UNION) || nextCharIs(input, COMPLEMENT) || nextCharIs(input, SYM_DIF)) ? true : false;
	}

	private boolean multiplicativeOperator(Scanner input) {
		return nextCharIs(input, INTERSECTION) ? true : false;
	}

	private void print(Scanner input) throws APException {
		String printout;
		
		character(input, PRINT);
		removeSpaces(input);
		printout = expression(input).toString();
		removeSpaces(input);
		eoln(input);
		out.println(printout);
	}

	private void removeSpaces(Scanner input) throws APException{
		
		while(input.hasNext() && nextCharIs(input, ' ')){
			character(input, ' ');
		}
	}

	private boolean nextCharIs(Scanner in, char c) {
		return (in.hasNext(Pattern.quote(c + ""))) ? true : false;
	}

	private char nextChar(Scanner in) {
		return in.next().charAt(0);
	}

	private boolean nextCharIsDigit(Scanner in) {
		return in.hasNext("[0-9]");
	}

	private boolean nextCharIsLetter(Scanner in) {
		return in.hasNext("[a-zA-Z]");
	}

	private void character(Scanner input, char c) throws APException {
		if(!nextCharIs(input, c)) {
			if(input.hasNext()) {
				throw new APException("Invalid input: '" + input.next() + "'");
			} else {
				throw new APException("Missing: '" + c + "'");
			}
		}
		nextChar(input);
	}
	
	private void string(Scanner input, String s) throws APException{
		int length = s.length();
		for(int i = 0; i < length; i++){
			if(nextCharIs(input, s.charAt(i))){
				nextChar(input);
			}else{
				throw new APException("Missing: " + s);
			}
		}
	}

	private void eoln(Scanner input) throws APException {
		if(input.hasNext()) {
			throw new APException("Invalid syntax: " + input.nextLine());
		}
	}

	public void start() {
		Scanner userInput = new Scanner(System.in);
		int lineNumber = 1;
		
		while(userInput.hasNext()) {
			try {
				parser(userInput.nextLine(), lineNumber);
			} catch(APException e) {
				out.println(e.getMessage() + " in line " + lineNumber);
			}
			lineNumber++;
		}
		System.exit(1);
	}


	public static void main(String[] args) {
		new InterpreterEnhanced().start();
	}
}
