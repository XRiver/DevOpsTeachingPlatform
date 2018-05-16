package com.interstellar.devopsjenkins.vo;

import java.util.List;

public class Computer {
    private List<ComputerVO> computer;

    public Computer() {
    }

    public Computer(List<ComputerVO> computer) {
        this.computer = computer;
    }

    public List<ComputerVO> getComputer() {
        return computer;
    }

    public void setComputer(List<ComputerVO> computer) {
        this.computer = computer;
    }
}
