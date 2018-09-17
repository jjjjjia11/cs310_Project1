package main;

import org.junit.*;
import main.FlightMap;
import org.junit.rules.ExpectedException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class TestFlightMap {
    FlightMap map;
    @Before
    public void runBeforeTest() throws IOException {
        try{
            map = new FlightMap("test/inputfile.txt");
        }catch (FileNotFoundException e){
            System.out.println(e.getStackTrace());
        }
    }

    /**
     * test if getOrigin() gets the right origin
     */
    @Test
    public void testGetOrigin(){
        if (map.getOrigin() == null){
            System.out.println("map origin is null");
        }else{
            System.out.println(map.getOrigin()+ " Someting");
        }
        assertEquals("P", map.getOrigin());
    }

    /**
     * Test if addEdge adds all edges between cities
     */
    @Test
    public void testAddEdge(){
        HashMap<String,HashMap<String,Integer>> adj;
        adj = map.getAdj();
        //check if addEdge() creates 2 original cities
        assertEquals(2, adj.keySet().size());

        //check if addEdge() creates 3 destination cities
        int numCities=0;
        for (String key : adj.keySet()){
            numCities = numCities + adj.get(key).size();
        }
        assertEquals(3, numCities);
    }
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    //test if FileNotFoundException is thrown
    @Test
    public void testCreateMap() throws Exception{
        FlightMap map2 = new FlightMap("test/doesNotExist.txt");
        thrown.expect(FileNotFoundException.class);
    }

    @After
    public void runAfterTest(){

    }
}