package net.fabricmc.Core.SolarCore;

import java.util.*;

public interface SolarDonor 
{
    int intensityOut();

    int radiusOut();

    List<BeamProperty> propertiesOut();

    SolarReciever castOut();
}
