package wheellllll.config;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by sweet on 4/10/16.
 */
public class ConfigTest {
    @Test
    public void getString() throws Exception {
        Config config1 = new Config();
        config1.setConfigPath("./test/test1");
        Config config2 = new Config();
        config2.setConfigPath("./test/test2");
        config1.configInit();
        config2.configInit();
        String host1 = config1.getConfig().getString("fooooooo");
        String host2 = config2.getConfig().getString("barrrrrr");
        assertEquals("test1",host1);
        assertEquals("test2",host2);


    }

    @Test
    public void getInt() throws Exception {
        Config config1 = new Config();
        config1.setConfigPath("./test/test1");
        Config config2 = new Config();
        config2.setConfigPath("./test/test2");
        config1.configInit();
        config2.configInit();
        int host1 = config1.getConfig().getInt("fooooooo1");
        int host2 = config2.getConfig().getInt("barrrrrr1");
        assertEquals(1024,host1);
        assertEquals(2048,host2);
       // Config.getConfig().setProperty("foo2", "1024");
        //assertEquals(1024, Config.getConfig().getInt("foo2"));
    }

    @Test
    public void getLong() throws Exception {
        Config config1 = new Config();
        config1.setConfigPath("./test/test1");
        Config config2 = new Config();
        config2.setConfigPath("./test/test2");
        config1.configInit();
        config2.configInit();
        long host1 = config1.getConfig().getLong("fooooooo1");
        long host2 = config2.getConfig().getLong("barrrrrr1");
        assertEquals(1024L,host1);
        assertEquals(2048L,host2);
        //Config.getConfig().setProperty("foo3", "1024");
        //assertEquals(1024L, Config.getConfig().getLong("foo3"));
    }

    @Test
    public void getBool() throws Exception {
        Config config1 = new Config();
        config1.setConfigPath("./test/test1");
        Config config2 = new Config();
        config2.setConfigPath("./test/test2");
        config1.configInit();
        config2.configInit();
        boolean host1 = config1.getConfig().getBool("fooooooo2");
        boolean host2 = config2.getConfig().getBool("barrrrrr2");
        assertEquals(true,host1);
        assertEquals(false,host2);
       // Config.getConfig().setProperty("foo4", "true");
        //assertEquals(true, Config.getConfig().getBool("foo4"));
    }

    @Test
    public void getFloat() throws Exception {
        Config config1 = new Config();
        config1.setConfigPath("./test/test1");
        Config config2 = new Config();
        config2.setConfigPath("./test/test2");
        config1.configInit();
        config2.configInit();
        float host1 = config1.getConfig().getFloat("fooooooo3");
        float host2 = config2.getConfig().getFloat("barrrrrr3");
        assertEquals(10.24,host1,0.0001);
        assertEquals(20.48,host2,0.0001);
        //Config.getConfig().setProperty("foo5", "10.24");
        //assertEquals(10.24, Config.getConfig().getFloat("foo5"), 0.0001);
    }

    @Test
    public void getDouble() throws Exception {
        Config config1 = new Config();
        config1.setConfigPath("./test/test1");
        Config config2 = new Config();
        config2.setConfigPath("./test/test2");
        config1.configInit();
        config2.configInit();
        double host1 = config1.getConfig().getDouble("fooooooo3");
        double host2 = config2.getConfig().getDouble("barrrrrr3");
        assertEquals(10.24,host1,0.0001);
        assertEquals(20.48,host2,0.0001);
        //Config.getConfig().setProperty("foo6", "10.24");
        //assertEquals(10.24, Config.getConfig().getDouble("foo6"), 0.0001);
    }

}