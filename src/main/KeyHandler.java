package main;

import java.awt.event.KeyEvent; // Representa eventos de tecla pressionada
import java.awt.event.KeyListener; // Interface para detectar entradas do teclado

// A classe KeyHandler implementa KeyListener para capturar eventos do teclado
public class KeyHandler implements KeyListener {
    
	GamePanel gp;
	
    // Variáveis booleanas para indicar quais teclas estão pressionadas
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    
    //DEBUG
    boolean showDebugText = false; 
    
    public KeyHandler(GamePanel gp) {
    	this.gp = gp;
    	
    }
    
 // Método obrigatório da interface KeyListener, mas não será usado
    @Override
    public void keyTyped(KeyEvent e) {    
    }

    // Método chamado quando uma tecla é pressionada
    @Override
    public void keyPressed(KeyEvent e) {
        
        int code = e.getKeyCode(); // Obtém o código da tecla pressionada
        //TELA DE INICIO
        if(gp.gameState == gp.titleState) {
        	titleState(code);        	
        }
        //PLAY STATE
        if (gp.gameState == gp.playState) {
            playState(code);           
        }
        
        //PAUSE STATE
        else if (gp.gameState == gp.pauseState) {        	 
            pauseState(code); // ESC para pausar
          }

        //DIALOGUE STATE
        else if(gp.gameState == gp.dialogueState) {
        	dialogueState(code);
         }
        
        // CHARACTER STATE
        else if(gp.gameState == gp.characterState) {
        	characterState(code);
        }
        else if(gp.gameState == gp.optionsState) {
        	optionsState(code);
        }
        
     }
     public void titleState (int code) {
    	 if (code == KeyEvent.VK_W) {
             gp.ui.commandNum--;
             if(gp.ui.commandNum < 0) {
             	
             	gp.ui.commandNum = 2;
             }
          }
          if (code == KeyEvent.VK_S) {
         	 gp.ui.commandNum++;
         	 if(gp.ui.commandNum > 2) {
              	
              	gp.ui.commandNum = 0;
              }
          }
          if(code == KeyEvent.VK_ENTER) {
         	 
         	 if(gp.ui.commandNum == 0) {
         		 gp.gameState = gp.playState;
         		 gp.stopMusic();
         		// gp.playMusic(0);
         	 }
         	 
         	 if(gp.ui.commandNum == 1) {
         		 
         		 //ADICIONAR DEPOIS
         	 }
         	 if(gp.ui.commandNum == 2) {
         		 
         		 System.exit(0);
         		 
         	 }
          }
     }
     public void playState (int code) {
    	 if (code == KeyEvent.VK_W) {
             upPressed = true;
         }
         if (code == KeyEvent.VK_S) {
             downPressed = true;
         }
         if (code == KeyEvent.VK_A) {
             leftPressed = true;
         }
         if (code == KeyEvent.VK_D) {
             rightPressed = true;

         }
         if (code == KeyEvent.VK_C) {
         	gp.gameState = gp.characterState;
         }

         // DEBUG
         if (code == KeyEvent.VK_T) {
         	showDebugText  = !showDebugText ;
         }

         if (code == KeyEvent.VK_R) {
         	gp.tileM.loadMap("/maps/world01.txt");
         }
         
         // ESC para pausar
         if (code == KeyEvent.VK_P) {
             gp.gameState = gp.pauseState;
         }
         if (code == KeyEvent.VK_ENTER) {
         	enterPressed = true;
         }
         if (code == KeyEvent.VK_ESCAPE) {
        	 gp.gameState = gp.optionsState;
         }
     }
     public void pauseState(int code) {
    	 if (code == KeyEvent.VK_P){
             gp.gameState = gp.playState;
         }
     }
     public void dialogueState(int code) {
    	 if (code == KeyEvent.VK_ENTER) {
     	    gp.ui.handleDialogueEnter();
     	}
     }
     public void characterState(int code) {
    	if(code == KeyEvent.VK_C) {
     		gp.gameState = gp.playState;
     	}
    	if(code == KeyEvent.VK_W) {
    		if(gp.ui.slotRow !=0) {
    		gp.ui.slotRow--;
    		}
    	}
    	if(code == KeyEvent.VK_A) {
    		if(gp.ui.slotCol !=0) {
    		gp.ui.slotCol--;
    		}
    	}
    	if(code == KeyEvent.VK_S) {
    		if(gp.ui.slotRow !=3) {
    			gp.ui.slotRow++;
    		}
    		
    	}
    	if(code == KeyEvent.VK_D) {
    		if(gp.ui.slotCol !=4) {
    			gp.ui.slotCol++;
    		}  		
    	}
     }

     public void optionsState(int code) {
    	 if(code == KeyEvent.VK_ESCAPE) {
    		 gp.gameState = gp.playState;
    	 }
    	 if(code == KeyEvent.VK_ENTER) {
    		 enterPressed = true;
    	 }
    	 
    	 int maxCommandNum = 0;
    	 switch(gp.ui.subState) {
    	 case 0: maxCommandNum = 5; break;
    	 }
    	 
    	 if(code == KeyEvent.VK_W) {
    		gp.ui.commandNum--;
    		gp.playSE(3);
    		if(gp.ui.commandNum < 0) {
    			gp.ui.commandNum = maxCommandNum;
    		}
    	 }
    	 if(code == KeyEvent.VK_S) {
    		gp.ui.commandNum++;
    		gp.playSE(3);
    		if(gp.ui.commandNum > maxCommandNum) {
    			gp.ui.commandNum = 0;
    		}
    	 }
    	 
    	 
     }
     
     
    // Método chamado quando uma tecla é solta
    @Override
    public void keyReleased(KeyEvent e) {
        
        int code = e.getKeyCode(); // Obtém o código da tecla solta
        
        // Verifica qual tecla foi solta e desativa a respectiva variável
        if (code == KeyEvent.VK_W) {    
            upPressed = false;
        }
        
        if (code == KeyEvent.VK_S) {    
            downPressed = false;
        }
        
        if (code == KeyEvent.VK_A) {    
            leftPressed = false;
        }
        
        if (code == KeyEvent.VK_D) {    
            rightPressed = false;
        }
    }
}

