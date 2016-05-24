package wheellllll.config;

import org.apache.commons.vfs2.*;
import org.apache.commons.vfs2.impl.DefaultFileMonitor;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * This class provide methods for config
 */
public class Config {


    private Properties mProps;
    private ReadWriteLock rwLock;
    private String configName = "application";
    private ConfigListener mConfigListener = null;



    /**
     * Set config file name
     * @param configName config file name
     */
    public void setConfigName(String configName) {this.configName = configName;
    }

    /**
     * Get a singleton config
     * @return Config
     */
    public Config getConfig() {
        return this;
    }

    public interface ConfigListener {
        public void OnConfigChanged();
    }

    public void setConfigListener(ConfigListener configListener) {
        mConfigListener = configListener;
    }

    public void configInit() {
        rwLock = new ReentrantReadWriteLock();
        loadConfig();
        try {
            FileSystemManager fsManager = VFS.getManager();
            FileObject listendir = null;
            File configFile = new File(configName);
            listendir = fsManager.resolveFile(configFile.getAbsolutePath());
            DefaultFileMonitor fm = new DefaultFileMonitor(new FileListener() {
                @Override
                public void fileCreated(FileChangeEvent fileChangeEvent) throws Exception {
                    // Do nothing
                }

                @Override
                public void fileDeleted(FileChangeEvent fileChangeEvent) throws Exception {
                    // Do nothing
                }

                @Override
                public void fileChanged(FileChangeEvent fileChangeEvent) throws Exception {
                    loadConfig();
                    if (mConfigListener != null) mConfigListener.OnConfigChanged();
                }
            });
            fm.setRecursive(true);
            fm.addFile(listendir);
            fm.start();
        } catch (FileSystemException e) {
            e.printStackTrace();
        }
    }

    private void loadConfig() {
        try {
            rwLock.readLock().lock();
            File configFile = new File(configName);
            FileReader reader = new FileReader(configFile);
            mProps = new Properties();
            mProps.load(reader);
            reader.close();
        } catch (java.io.FileNotFoundException e) {
            mProps = new Properties();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public void setProperty(String key, String value) {
        rwLock.writeLock().lock();
        try {
            mProps.setProperty(key, value);
            File configFile = new File(configName);
            FileWriter writer = new FileWriter(configFile);
            mProps.store(writer, "host settings");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public String getString(String key) {
        rwLock.readLock().lock();
        String value = mProps.getProperty(key);
        rwLock.readLock().unlock();
        return value;
    }

    public String getString(String key, String defaultValue) {
        rwLock.readLock().lock();
        String value = mProps.getProperty(key, defaultValue);
        value = value != null ? value : defaultValue;
        rwLock.readLock().unlock();
        return value;
    }

    public int getInt(String key) {
        rwLock.readLock().lock();
        int rValue = Integer.parseInt(mProps.getProperty(key));
        rwLock.readLock().unlock();
        return rValue;
    }

    public int getInt(String key, int defaultValue) {
        rwLock.readLock().lock();
        String value = mProps.getProperty(key);
        int rValue = value != null ? Integer.parseInt(value) : defaultValue;
        rwLock.readLock().unlock();
        return rValue;
    }

    public long getLong(String key) {
        rwLock.readLock().lock();
        long rValue = Long.parseLong(mProps.getProperty(key));
        rwLock.readLock().unlock();
        return rValue;
    }

    public long getLong(String key, long defaultValue) {
        rwLock.readLock().lock();
        String value = mProps.getProperty(key);
        long rValue = value != null ? Long.parseLong(value) : defaultValue;
        rwLock.readLock().unlock();
        return rValue;
    }

    public boolean getBool(String key) {
        rwLock.readLock().lock();
        boolean rValue = Boolean.parseBoolean(mProps.getProperty(key));
        rwLock.readLock().unlock();
        return rValue;
    }

    public boolean getBool(String key, boolean defaultValue) {
        rwLock.readLock().lock();
        String value = mProps.getProperty(key);
        boolean rValue = value != null ? Boolean.parseBoolean(value) : defaultValue;
        rwLock.readLock().unlock();
        return rValue;
    }

    public float getFloat(String key) {
        rwLock.readLock().lock();
        float rValue = Float.parseFloat(mProps.getProperty(key));
        rwLock.readLock().unlock();
        return rValue;
    }

    public float getFloat(String key, float defaultValue) {
        rwLock.readLock().lock();
        String value = mProps.getProperty(key);
        float rValue = value != null ? Float.parseFloat(value) : defaultValue;
        rwLock.readLock().unlock();
        return rValue;
    }

    public double getDouble(String key) {
        rwLock.readLock().lock();
        double rValue = Double.parseDouble(mProps.getProperty(key));
        rwLock.readLock().unlock();
        return rValue;
    }

    public double getDouble(String key, double defaultValue) {
        rwLock.readLock().lock();
        String value = mProps.getProperty(key);
        double rValue = value != null ? Double.parseDouble(value) : defaultValue;
        rwLock.readLock().unlock();
        return rValue;
    }
}