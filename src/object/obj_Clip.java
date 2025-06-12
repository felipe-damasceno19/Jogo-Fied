package object;

import entity.Entity;
import main.GamePanel;

public class obj_Clip extends Entity {

    public obj_Clip(GamePanel gp) {
        super(gp);

        name = "Clip";
        ObjImage = setup("/objects/key"); // certifique-se de que essa imagem existe em /resources/objects
        description = "[" + name + "]\nPode ser usado para arrombar gaveteiros.";

        collision = true;

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 48;
        solidArea.height = 48;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
