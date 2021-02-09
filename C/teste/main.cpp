//Prof. Marco Antonio G. de Carvalho
//Atividade UNIVESP, Computacao Grafica, 2019
//Aluno: MARIO HELIO SIMOES; RA: 1600356
#include<stdlib.h>
#include<GL/glut.h>

/*Dados dos objetos, cores e formas.*/
static int gira=0,gX=0,gY=0,gZ = 0; //variavéis para rotação
static float muretaQuadra[2]= {1.5,0.1};
static float corMureta[3]= {0.2,0.2,0.2};
static float quadra[3] = {12,0.1,8};
static float corQuadra[3]= {0.39,0.58,0.92};
static float escadaAltura = 0.1;
static float escadaLargura = 0.5;
static float escadaComprimento = 12;
static float chao[3] = {25,6.1,20};
static float corChao[3]= {0.0,0.58,0.0};
static float corArquibancada[3]= {0.36,0.2,0.09};
static float corBaseArq[3]= {0.75,0.75,0.75};
static float corBola[3]= {1,0,0};
static float corHaste1[3]= {0.0,0.0,0.0};
static float corHaste2[3]= {0.0,0.0,0.0};
static float corTabela1[3]= {0.6,0.6,1};
static float corTabela2[3]= {0.6,0.6,1};
static float corAro[3]= {0,0,0};



void init(void)
{
    glClearColor (0.8, 1.0, 1.0, 1.0);
}

void display(void)
{

    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    glPushMatrix();
    glRotatef ((GLfloat) gira, gX, gY, gZ);
    glColor3f (1.0, 1.0, 1.0);

    /* **************** Chao *********************/
    glPushMatrix();
    glTranslatef (0.0, -3.2, 0.0); // posicao na tela (x,z,y)
    glColor3f (corChao[0], corChao[1], corChao[2]); //Definicao de Cor do objeto
    glScalef (chao[0], chao[1], chao[2]); // Tamanho do objeto (x, z, y)
    glutSolidCube (1.0); // escala de proporcao do objeto
    glPopMatrix();
    /* **************** Fim Chao ***************** */

    /* *****************  Quadra ***************** */
    glPushMatrix();
    glColor3f (corQuadra[0], corQuadra[1], corQuadra[2]);
    glScalef (quadra[0], quadra[1], quadra[2]);
    glutSolidCube (1.0);
    glPopMatrix();

    glPushMatrix();
    glColor3f (1,1,1);
    glTranslatef (0.0, quadra[1], 0.0); // posicao na tela (x,y,z)
    glScalef (0.05, 0.01, 7.5);
    glutSolidCube (1.0);
    glPopMatrix();

    glPushMatrix();
    glColor3f (1,1,1);
    glTranslatef (5.5, quadra[1], 0.0);
    glScalef (1, 0.01, 7.5);
    glutSolidCube (1.0);
    glPopMatrix();

    glPushMatrix();
    glColor3f (1,1,1);
    glTranslatef (-5.5, quadra[1], 0.0); 
    glScalef (1, 0.01, 7.5);
    glutSolidCube (1.0);
    glPopMatrix();

    glPushMatrix();
    glColor3f (1,1,1);
    glTranslatef (0, quadra[1], 3.75); 
    glScalef (11.5, 0.01, 0.5);
    glutSolidCube (1.0);
    glPopMatrix();

    glPushMatrix();
    glColor3f (1,1,1);
    glTranslatef (0, quadra[1], -3.75); 
    glScalef (11.5, 0.01, 0.5);
    glutSolidCube (1.0);
    glPopMatrix();
    /* ***************** Fim Quadra***************** */

    /* *****************  Mureta***************** */
    glPushMatrix();
    glColor3f(corMureta[0],corMureta[1],corMureta[2]);
    glTranslatef (0, 0.0, quadra[2]/2); 
    glScalef (quadra[0], muretaQuadra[0], muretaQuadra[1]);
    glutSolidCube (1.0);
    glPopMatrix();

    glPushMatrix();
    glColor3f(corMureta[0],corMureta[1],corMureta[2]);
    glTranslatef (0, 0.0, -quadra[2]/2);
    glScalef (quadra[0], muretaQuadra[0], muretaQuadra[1]);
    glutSolidCube (1.0);
    glPopMatrix();

    glPushMatrix();
    glColor3f(1,0,0);
    glTranslatef (quadra[0]/2, 0.0, 0); 
    glScalef (muretaQuadra[1], muretaQuadra[0], quadra[2]);
    glutSolidCube (1.0);
    glPopMatrix();

    glPushMatrix();
    glColor3f(0,1,0);
    glTranslatef (-quadra[0]/2, 0.0, 0); 
    glScalef (muretaQuadra[1], muretaQuadra[0], quadra[2]);
    glutSolidCube (1.0);
    glPopMatrix();

    /* ***************** Fim Mureta ***************** */

    /* ***************** arquibancada 1 ***************** */
    float x=0.0,y=0.5,z=6;
    int i;


    for(i = 1; i<8; i++)  //Degraus da arquibancada
    {
        glPushMatrix();
        glColor3f(corArquibancada[0],corArquibancada[1],corArquibancada[2]);
        glTranslatef (x, y, z);
        glScalef (escadaComprimento, escadaAltura, escadaLargura);
        glutSolidCube (1.0);
        glPopMatrix();

        glPushMatrix();
        glColor3f(corBaseArq[0],corBaseArq[1],corBaseArq[2]);
        glTranslatef (x, 0, z);
        glScalef (escadaComprimento, y*2, escadaAltura);
        glutSolidCube (1.0);
        glPopMatrix();

        y=y+0.3;
        z = z+0.5;
    }
    /* ***************** Fim Arquibancada 1 ***************** */


    /* ***************** arquibancada 2 *****************  */

    x=0.0;
    y=0.5;
    z=-6;

    for(i = 1; i<8; i++)  //Degraus da arquibancada
    {
        glPushMatrix();
        glColor3f(corArquibancada[0],corArquibancada[1],corArquibancada[2]);
        glTranslatef (x, y, z);
        glScalef (escadaComprimento, escadaAltura, escadaLargura);
        glutSolidCube (1.0);
        glPopMatrix();

        glPushMatrix();
        glColor3f(corBaseArq[0],corBaseArq[1],corBaseArq[2]);
        glTranslatef (x, 0, z);
        glScalef (escadaComprimento, y*2, escadaAltura);
        glutSolidCube (1.0);
        glPopMatrix();


        y=y+0.3;
        z = z-0.5;
    }
    /* ***************** Fim Arquibancada 2 ***************** */

    /* *****************  arquibancada 3 *****************  */

    x=0.0;
    y=0.5;
    z=-8;

    for(i = 1; i<8; i++)  //Degraus da arquibancada
    {
        glPushMatrix();
        glColor3f(corArquibancada[0],corArquibancada[1],corArquibancada[2]);
        glTranslatef (z, y, x);
        glScalef (escadaLargura, escadaAltura, escadaComprimento);
        glutSolidCube (1.0);
        glPopMatrix();

        glPushMatrix();
        glColor3f(corBaseArq[0],corBaseArq[1],corBaseArq[2]);
        glTranslatef (z, 0, x);
        glScalef (escadaAltura, y*2, escadaComprimento);
        glutSolidCube (1.0);
        glPopMatrix();

        y=y+0.3;
        z = z-0.5;
    }
    /* ***************** Fim Arquibancada 3 ***************** */



    /* ***************** Haste 1 *****************  */
    glPushMatrix();
    glColor3f (corHaste1[0], corHaste1[1], corHaste1[2]);
    glTranslatef (-5.0, 1.6, 0.0);
    glScalef (0.2, 3.0, 0.2);
    glutSolidCube (1.0);
    glPopMatrix();

    glPushMatrix();
    glTranslatef (-4.8, 3.2, 0.0);
    glScalef (0.6, 0.2, 0.2);
    glutSolidCube (1.0);
    glPopMatrix();
    /* ***************** Fim Haste 1 ***************** */


    /* ***************** Tabela 1 *****************  */
    glPushMatrix();
    glColor3f (corTabela1[0], corTabela1[1], corTabela1[2]);
    glTranslatef (-4.4, 3.2, 0.0);
    glScalef (0.2, 1.0, 1.0);
    glutSolidCube (1.0);
    glPopMatrix();
    /* ***************** Fim Tabela 1 ***************** */

    /* ***************** Aro da tabela 1***************** */
    glPushMatrix();
    glColor3f (corAro[0], corAro[1], corAro[2]);
    glTranslated(4,3,0);
    glRotated(90,1,0,0);
    glutSolidTorus(0.1,0.3,7,7);
    glPopMatrix();
    /* ******************Fim Aro da Tabela1 ***************** */


    /* *****************  Haste 2 ***************** */
    glPushMatrix();
    glColor3f (corHaste2[0], corHaste2[1], corHaste2[2]);
    glTranslatef (5.0, 1.6, 0.0);
    glScalef (0.2, 3.0, 0.2);
    glutSolidCube (1.0);
    glPopMatrix();

    glPushMatrix();
    glTranslatef (4.8, 3.2, 0.0);
    glScalef (0.6, 0.2, 0.2);
    glutSolidCube (1.0);
    glPopMatrix();
    /* ***************** Fim Haste 2 ***************** */



    /* *****************  tabela 2 *****************  */
    glPushMatrix();
    glColor3f (corTabela2[0], corTabela2[1], corTabela2[2]);
    glTranslatef (4.4, 3.2, 0.0);
    glScalef (0.2, 1.0, 1.0);
    glutSolidCube (1.0);
    glPopMatrix();
    /* ***************** Fim tabela 2 ***************** */

    /* ***************** Aro da tabela 2 ***************** */
    glPushMatrix();
    glColor3f (corAro[0], corAro[1], corAro[2]);
    glTranslated(-4,3,0);
    glRotated(90,1,0,0);
    glutSolidTorus(0.1,0.3,7,7);
    glPopMatrix();
    /* ******************Fim Aro da Tabela2 ***************** */


    /* ***************** Bola ***************** */
    glPushMatrix();
    glColor3f (corBola[0], corBola[1], corBola[2]);
    glTranslatef (0.0, 0.3, 0.0);
    glutSolidSphere(0.25, 10, 10);
    glPopMatrix();
    /* ***************** FIM Bola ***************** */


    /* ***************** Texto de Identificacao do Autor/Aluno *****************  */


    glRasterPos2f(0, 8);
    // String a ser escrita na tela
    char* p = (char*) "UNIVESP - Computacao Grafica - EES101";
    while (*p != '\0')
    {
        glutBitmapCharacter(GLUT_BITMAP_HELVETICA_18,*p++);
    }
    glRasterPos2f(0, 7);
    // String a ser escrita na tela
    p = (char*) "Mario Helio Simoes - RA: 1600356";
    while (*p != '\0')
    {
        glutBitmapCharacter(GLUT_BITMAP_HELVETICA_18,*p++);
    }

    glRasterPos2f(-5, 5);
    // String a ser escrita na tela
    p = (char*) "Use as teclas 'x','X','y','Y','z','Z' ou 'c' para rotacionar entre os eixos";
    while (*p != '\0')
    {
        glutBitmapCharacter(GLUT_BITMAP_HELVETICA_18,*p++);
    }

    /* ***************** FIM -Texto de Identificacao do Autor/Aluno *****************  */
    glPopMatrix();
    glutSwapBuffers();
    
}/* *****************Fim função display(void) ***************** */

void iluminacao(void)
{
    GLfloat luzAmbiente[4]= {0.1,0.1,0.1,1};
    GLfloat luzDifusa[4]= {0.1,0.1,0.1,1.0};
    GLfloat posicaoLuz[4]= {20.0, 20.0, 50.0, 1.0};

    glShadeModel(GL_SMOOTH);
    glLightModelfv(GL_LIGHT_MODEL_AMBIENT, luzAmbiente);
    glLightfv(GL_LIGHT0, GL_AMBIENT, luzAmbiente);
    glLightfv(GL_LIGHT0, GL_DIFFUSE, luzDifusa );
    glLightfv(GL_LIGHT0, GL_POSITION, posicaoLuz );

    glEnable(GL_COLOR_MATERIAL);
    glEnable(GL_LIGHTING);
    glEnable(GL_LIGHT0);
    glEnable(GL_DEPTH_TEST);

    
}/* *****************Fim função iluminacao() ***************** */

void reshape (int w, int h)
{
    glViewport (0, 0, (GLsizei) w, (GLsizei) h);
    glMatrixMode (GL_PROJECTION);
    glLoadIdentity ();
    gluPerspective(65.0, (GLfloat) w/(GLfloat) h, 1.0, 25.0);
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
    glTranslatef (0.0, -4.0, -15.0);
    
}/* *****************Fim função reshape(int w, int h) ***************** */
void keyboard(unsigned char key, int x, int y)
{
    switch (key)
    {

    case 'c':
        gira = 0;
        gX=0;
        gY=0;
        gZ=0;
        glutPostRedisplay();
        break;
    case 'x':
        gira = (gira + 5) % 360;
        gX+=1;
        glutPostRedisplay();
        break;
    case 'X':
        gira = (gira - 5) % 360;
        gX+=-1;
        glutPostRedisplay();
        break;
    case 'y':
        gira = (gira + 5) % 360;
        gY+=1;
        glutPostRedisplay();
        break;
    case 'Y':
        gira = (gira - 5) % 360;
        gY+=-1;
        glutPostRedisplay();
        break;
    case 'z':
        gira = (gira + 5) % 360;
        gZ+=1;
        glutPostRedisplay();
        break;
    case 'Z':
        gira = (gira - 5) % 360;
        gZ+=-1;
        glutPostRedisplay();
        break;
    case 27:           // tecla Esc (encerra o programa)
        exit(0);
        break;
    }
    
}/* *****************Fim função keyboard(unsigned char, int int) ***************** */

int main(int argc, char** argv)
{
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);
    glutInitWindowSize (1000, 500);
    glutInitWindowPosition (0, 0);
    glutCreateWindow (argv[0]);
    init ();
    glutDisplayFunc(display);
    glutReshapeFunc(reshape);
    glutKeyboardFunc(keyboard);
    iluminacao();
    glutMainLoop();
    return 0;
    
}/* *****************Fim função main() ***************** */
