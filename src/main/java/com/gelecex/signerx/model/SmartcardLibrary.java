package com.gelecex.signerx.model;

/**
 * Created by obetron on 5.01.2019
 */
public class SmartcardLibrary {

    private String name;
    private String arch;

    public SmartcardLibrary() {
    }

    public SmartcardLibrary(String name, String arch) {
        this.name = name;
        this.arch = arch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArch() {
        return arch;
    }

    public void setArch(String arch) {
        this.arch = arch;
    }
}
