package org.elasticsearch.plugin.serbianplugin.analyzer.config;

import org.apache.lucene.analysis.TokenStream;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractTokenFilterFactory;
import org.elasticsearch.plugin.serbianplugin.analyzer.RemoveAccentsFilter;

/**
*
* @author Violeta N
*/
public class SerbianTokenFilterFactory extends AbstractTokenFilterFactory {

	public SerbianTokenFilterFactory(IndexSettings indexSettings, String name,
			Settings settings) {
		super(indexSettings, name, settings);
	}

	@Override
	public TokenStream create(TokenStream tokenStream) {
		return new RemoveAccentsFilter(tokenStream);
	}

}
