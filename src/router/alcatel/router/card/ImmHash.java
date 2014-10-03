package router.alcatel.router.card;

import java.util.HashMap;

public final class ImmHash {

	public final static HashMap<String, String> immTypes = new HashMap<String, String>();
	
	static {
		immTypes.put("imm12-10gb-sf+", "imm12-10gb-xp-sf+ ");
		immTypes.put("imm8-10gb-xfp", "imm4-10gb-xp-xfp");
	}
}
