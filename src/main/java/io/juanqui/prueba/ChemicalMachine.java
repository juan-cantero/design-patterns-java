package io.juanqui.prueba;

import java.util.*;

public class ChemicalMachine {

    private ArrayList<String> chemicals;

    private String mix;

    public ChemicalMachine() {
        chemicals = new ArrayList<>();

    }

    public void add(String chemical) {
        chemicals.add(chemical.toLowerCase());
    }

    public void applyHeat() {
        Map<String,String> heatTransformations = new HashMap<>();
        heatTransformations.put("green+cyan", "orange");
        heatTransformations.put("orange", "read+blue");
        heatTransformations.put("green+yellow", "brown");
        heatTransformations.put("brown","magenta");

        if (chemicals.size() > 1) {
            String chemicalKey  = String.join("+", chemicals);

            mix = heatTransformations.getOrDefault(chemicalKey, "UNKNOWN");
        }else {
            mix = heatTransformations.getOrDefault(chemicals.get(0), "UNKNOWN");
        }
    }

    public ArrayList<String> emptyMachine() {
        chemicals.clear();
        if (mix.contains("+")) {

            return new ArrayList<>(List.of(mix.split("\\+")));
        }
        return new ArrayList<>(List.of(mix));
    }

    public static void main(String[] args) {
        ChemicalMachine machine = new ChemicalMachine();

        machine.add("GREEN");
        machine.add("YELLOW");
        machine.applyHeat();
        System.out.println(machine.emptyMachine()); // emptyMachine should return {"BROWN"}

        machine.add("RED");
        machine.add("YELLOW");
        machine.applyHeat();
        System.out.println(machine.emptyMachine()); // emptyMachine should return {"UNKOWN"}

        machine.add("ORANGE");
        machine.applyHeat();
        System.out.println(machine.emptyMachine()); // emptyMachine should return {"UNKOWN"}
    }
}
