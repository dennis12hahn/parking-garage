package com.example.parkinggarage.model.utils;

/**
 * Used to store hold one instance of the IncrementalDataContainer for the garage to use.
 *
 * @author Dennis Hahn <A href="mailto:hahnd62@mail.sunysuffolk.edu">hahnd62@mail.sunysuffolk.edu</A>
 * @version 05/2019
 */
public abstract class SingletonIncrementalDataContainer {

	private static IncrementalDataContainer dataContainer = new IncrementalDataContainer();

	public static IncrementalDataContainer getDataContainer() {
		return dataContainer;
	}

	public static void setDataContainer(IncrementalDataContainer dataContainer) {
		SingletonIncrementalDataContainer.dataContainer = dataContainer;
	}
}
