package net.fabricmc.Core.SolarCore;

import java.util.*;

public interface SolarReciever 
{
    int intensityIn();

    int radiusIn();

    List<BeamProperty> propertiesIn();

    void acceptBeam(SolarDonor donor);
}
