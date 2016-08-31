package hjp.mac.java.nlp.toolkit;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

public class OpenNLPChunker {

	public static String[] Tokener(String sent) {
		InputStream modelIn = null;
		TokenizerModel model = null;

		try {
			modelIn = new FileInputStream("../Workshop/Library/Apache/OpenNLP/bin/en-token.bin");
			model = new TokenizerModel(modelIn);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (modelIn != null) {
				try {
					modelIn.close();
				} catch (IOException e) {
				}
			}
		}

		Tokenizer tokenizer = new TokenizerME(model);
		String tokens[] = tokenizer.tokenize(sent);

		return tokens;
	}

	public static String[] POSTagger(String[] tok) {
		InputStream modelIn = null;
		POSModel model = null;

		try {
			modelIn = new FileInputStream("../Workshop/Library/Apache/OpenNLP/bin/en-pos-maxent.bin");
			model = new POSModel(modelIn);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (modelIn != null) {
				try {
					modelIn.close();
				} catch (IOException e) {
				}
			}
		}

		POSTaggerME tagger = new POSTaggerME(model);
		String tags[] = tagger.tag(tok);

		return tags;
	}

	public static String[] Chunker(String[] tok, String[] tag) throws IOException {
		InputStream modelIn = null;
		ChunkerModel model = null;

		try {
			modelIn = new FileInputStream("../Workshop/Library/Apache/OpenNLP/bin/en-chunker.bin");
			model = new ChunkerModel(modelIn);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (modelIn != null) {
				try {
					modelIn.close();
				} catch (IOException e) {
				}
			}
		}

		ChunkerME chunker = new ChunkerME(model);
		String chk[] = chunker.chunk(tok, tag);

		return chk;
	}

	public static void main(String[] args) throws IOException {
		String sent = "The goal of the OpenNLP project will be to create a mature toolkit for the abovementioned tasks.";

		String[] tokens = Tokener(sent);
		for (String tok : tokens) {
			System.out.println(tok);
		}

		String[] tags = POSTagger(tokens);
		for (String tag : tags) {
			System.out.println(tag);
		}

		String[] chunkers = Chunker(tokens, tags);
		for (String chk : chunkers) {
			System.out.println(chk);
		}

		System.out.println("Token\nPos\nChunker");
		for (int i = 0; i < tokens.length; i++) {
			System.out.println(tokens[i] + "\t" + tags[i] + "\t" + chunkers[i]);
		}
	}

}
