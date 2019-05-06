package com.example.parkinggarage.model.utils;

public abstract class SingletonIncrementalDataContainer {

	private static IncrementalDataContainer dataContainer = new IncrementalDataContainer();

	public static IncrementalDataContainer getDataContainer() {
		return dataContainer;
	}

	public static void setDataContainer(IncrementalDataContainer dataContainer) {
		SingletonIncrementalDataContainer.dataContainer = dataContainer;
	}
}
