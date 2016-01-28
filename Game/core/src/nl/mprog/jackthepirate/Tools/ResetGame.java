package nl.mprog.jackthepirate.Tools;

import nl.mprog.jackthepirate.MainActivity;

/**
 * Lirry Pinter
 * 10565051
 * lirry.pinter@gmail.com
 *
 * In the ResetGame class all the booleans relevant for gameplay are reset to default.
 */
public class ResetGame {
    public static void ResetGame(){
        MainActivity.right_green = true;
        MainActivity.right_blue = true;
        MainActivity.up_red = true;
        MainActivity.left_green = false;
        MainActivity.left_blue = false;
        MainActivity.down_red = false;
        MainActivity.featherBluePicked = false;
        MainActivity.featherRedPicked = false;
        MainActivity.featherGreenPicked = false;
    }
}
