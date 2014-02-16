import ca.cmput301w14t09.model.Area;
import junit.framework.TestCase;


public class testArea extends TestCase {

	public testArea(String name) {
		super(name);
	}
	
	public void testCheckAreaName(){
		Area area= new Area();

		String content = "areaname";

		area.setAreaName(content);

		String areaString = area.getAreaName();

		assertTrue(areaString.equals(content));
	}
}
