package javafx;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.junit.Assert;
import org.junit.Test;

/**
 * JavaFx is a dependency and this confirms it has been added as a dependency
 * and is found.
 * 
 * @author bsmith
 *
 */
public class JavaFXPresent {

	@Test
	public void testCreateCollection() {
		List<String> theList = new ArrayList<>();

		theList.add("A string");
		ObservableList<String> simpleList = FXCollections
				.observableList(theList);
		Assert.assertEquals(1, simpleList.size());
	}

}
