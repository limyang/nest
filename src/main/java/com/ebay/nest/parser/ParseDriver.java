package com.ebay.nest.parser;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenRewriteStream;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.TreeAdaptor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ebay.nest.NestContext;

public class ParseDriver {

	private static final Log LOG = LogFactory.getLog(ParseDriver.class.getName());

	public class ANTLRNoCaseStringStream extends ANTLRStringStream {
		private String[] lines;

		public ANTLRNoCaseStringStream(String input) {
			super(input);
			List<String> lineList = new ArrayList<String>();
			int i = 0;
			int prev = -1;
			int curr = -1;
			while (i < this.data.length) {

				if (this.data[i] == '\n') {
					prev = curr + 1;
					curr = i;
					lineList.add(new String(data, prev, curr - prev + 1));
				} 
				i++;

			}
			lines = lineList.toArray(new String[lineList.size()]);

		}

		public String getLineString(int i) {
			return lines[i-1];
		}
		
		public int getLineCount(){
			return lines.length;
		}


		public int LA(int i) {

			int returnChar = super.LA(i);
			if (returnChar == CharStream.EOF) {
				return returnChar;
			} else if (returnChar == 0) {
				return returnChar;
			}

			return Character.toUpperCase((char) returnChar);
		}
	}

	public class DDILexerX extends SQLLexer {

		private final ArrayList<ParseError> errors;

		public DDILexerX() {
			super();
			errors = new ArrayList<ParseError>();
		}

		public DDILexerX(CharStream input) {
			super(input);
			errors = new ArrayList<ParseError>();
		}

		public void displayRecognitionError(String[] tokenNames, RecognitionException e) {
			errors.add(new ParseError(this, e, tokenNames));
		}

		public String getErrorMessage(RecognitionException e, String[] tokenNames) {
			String msg = null;
			if (e instanceof NoViableAltException) {
				msg = "character " + getCharErrorDisplay(e.c) + " not supported here";
			} else {
				msg = super.getErrorMessage(e, tokenNames);
			}
			return msg;
		}

		public ArrayList<ParseError> getErrors() {
			return errors;
		}

	}

	static final TreeAdaptor adaptor = new CommonTreeAdaptor() {
		
		public Object create(Token payload) {
			return new ASTNode(payload);
		}

		public Object dupNode(Object t) {

			return create(((CommonTree) t).token);
		};

		public Object errorNode(TokenStream input, Token start, Token stop, RecognitionException e) {
			return new ASTErrorNode(input, start, stop, e);
		};
	};

	public ASTNode parse(String command) throws ParseException {
		return parse(command, null);
	}

	public ASTNode parse(String cql, NestContext ctx) throws ParseException {

		DDILexerX lexer = new DDILexerX(new ANTLRNoCaseStringStream(cql));
		TokenRewriteStream tokens = new TokenRewriteStream(lexer);
		if (ctx != null) {
			ctx.setTokenRewriteStream(tokens);
		}
		SQLParser parser = new SQLParser(tokens);
		parser.setTreeAdaptor(adaptor);
		SQLParser.all_return r = null;
		try {
			r = parser.all();
		} catch (RecognitionException e) {
			e.printStackTrace();
			throw new ParseException(parser.errors);
		}

		if (lexer.getErrors().size() == 0 && parser.errors.size() == 0) {
			LOG.info("Parse Completed");
		} else if (lexer.getErrors().size() != 0) {
			throw new ParseException(lexer.getErrors());
		} else {
			throw new ParseException(parser.errors);
		}

		return (ASTNode) r.getTree();
	}

}
