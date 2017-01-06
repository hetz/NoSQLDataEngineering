/*
 * generated by Xtext 2.10.0
 */
package es.um.nosql.schemainference.dslparameter.parser.antlr;

import com.google.inject.Inject;
import es.um.nosql.schemainference.dslparameter.parser.antlr.internal.InternalODMParameterParser;
import es.um.nosql.schemainference.dslparameter.services.ODMParameterGrammarAccess;
import org.eclipse.xtext.parser.antlr.AbstractAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;

public class ODMParameterParser extends AbstractAntlrParser {

	@Inject
	private ODMParameterGrammarAccess grammarAccess;

	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}
	

	@Override
	protected InternalODMParameterParser createParser(XtextTokenStream stream) {
		return new InternalODMParameterParser(stream, getGrammarAccess());
	}

	@Override 
	protected String getDefaultRuleName() {
		return "MongooseModel";
	}

	public ODMParameterGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(ODMParameterGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
