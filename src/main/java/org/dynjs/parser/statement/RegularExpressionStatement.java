package org.dynjs.parser.statement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.qmx.jitescript.CodeBlock;

import org.antlr.runtime.tree.Tree;
import org.dynjs.parser.ParserException;
import org.dynjs.parser.Statement;

public class RegularExpressionStatement extends BaseStatement implements
		Statement {

	// TODO: Move the parsing logic to the parser
	static class RegExp {
		private static final String REG_EXP_PATTERN = "^\\/(.*)\\/([igm]{0,})$";

		static RegExp parse(String text) {
			Pattern pattern = Pattern.compile(REG_EXP_PATTERN);
			Matcher matcher = pattern.matcher(text);
			if (matcher.matches()) {
				return new RegExp(matcher.group(1),
						convertFlags(matcher.group(2)),
						needGlobalMatch(matcher.group(2)));
			}

			return null;
		}

		private static Integer convertFlags(String flags) {
			if (flags == null || flags.isEmpty()) {
				return null;
			}

			int result = 0;
			for (char flag : flags.toCharArray()) {
				result |= patternFlag(flag);
			}

			return result;
		}

		private static int patternFlag(char c) {
			if (c == 'i') {
				return Pattern.CASE_INSENSITIVE;
			}
			if (c == 'm') {
				return Pattern.MULTILINE;
			}

			if (c == 'g') {
				return 0;
			}

			return 0;
		}

		private static boolean needGlobalMatch(String flags) {
			return flags.contains("g");
		}

		private final String regex;
		private final Integer flags;
		private final boolean isGlobalMatch;

		private RegExp(String regex, Integer flags, boolean isGlobalMatch) {
			this.regex = regex;
			this.flags = flags;
			this.isGlobalMatch = isGlobalMatch;
		}

		String getRegex() {
			return regex;
		}

		Integer getFlags() {
			return flags;
		}

		boolean isGlobalMatch() {
			return isGlobalMatch;
		}
	}

	private final RegExp regExp;

	public RegularExpressionStatement(Tree tree) {
		super(tree);
		this.regExp = RegExp.parse(tree.getText());
		if (regExp == null) {
			throw new ParserException("Invalid regular expression", tree);
		}
	}

	@Override
	public CodeBlock getCodeBlock() {
		return null;
	}
}
