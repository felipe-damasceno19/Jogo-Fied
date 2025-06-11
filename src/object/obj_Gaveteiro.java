package object;

import java.util.ArrayList;

import entity.Entity;
import main.GamePanel;

public class obj_Gaveteiro extends Entity {
	GamePanel gp;
    public ArrayList<Entity> storedItems = new ArrayList<>();
    public boolean opened = false;

    public obj_Gaveteiro(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = "Gaveteiro";
        collision = true;
        ObjImage = setup("/objects/Gaveteiro");
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 48;
        solidArea.height = 48;
    }

    public void open() {
        if (!opened) {
            for (Entity item : storedItems) {
                gp.player.obterItem(item);
            }
            opened = true;
            ObjImage = setup("/objects/Gaveteiro_aberto");
        } else {
            gp.ui.showMessage("Nada mais aqui.");
        }
    }
}
