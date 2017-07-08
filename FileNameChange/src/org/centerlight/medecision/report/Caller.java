package org.centerlight.medecision.report;

import java.io.IOException;

public class Caller {

	public static void main(String[] args) throws IOException {
		FileNameChange fnc = new FileNameChange();
		fnc.changeFileName(fnc.getFileFolder());

	}

}
