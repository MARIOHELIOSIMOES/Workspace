//Prof. Marco Antonio G. de Carvalho
//Atividade UNIVESP, Comp. Gráfica, 2019

#include<stdlib.h>
#include<GL/glut.h>

static int gira = 0;

void init(void){
  glClearColor (0.0, 0.0, 0.0, 0.0);
}

void display(void){
  glClear (GL_COLOR_BUFFER_BIT);
  glPushMatrix();
  
   glRotatef ((GLfloat) gira, 0.0, 1.0, 0.0);

  /* base */
  glPushMatrix();
  //glTranslatef (-2.0, 0.0, 0.0);
  glScalef (12.0, 0.2, 8.0);
  glutWireCube (1.0);
  glPopMatrix();
   
   /* haste */ 
  glPushMatrix();
  glTranslatef (-5.0, 1.6, 0.0);
  glScalef (0.2, 3.0, 0.2);
  glutWireCube (1.0);
  glPopMatrix();

   
  glPushMatrix();
  glTranslatef (-4.8, 3.2, 0.0);
  glScalef (0.6, 0.2, 0.2);
  glutWireCube (1.0);
  glPopMatrix();

   /* tabela */   
  glPushMatrix();
  glTranslatef (0.0, 3.2, 0.0);
  glScalef (0.2, 1.0, 1.0);
  glutWireCube (1.0);
  glPopMatrix();

  glPopMatrix();
  glutSwapBuffers();
}

void reshape (int w, int h){
  glViewport (0, 0, (GLsizei) w, (GLsizei) h);
  glMatrixMode (GL_PROJECTION);
  glLoadIdentity ();
  gluPerspective(65.0, (GLfloat) w/(GLfloat) h, 1.0, 25.0);
  glMatrixMode(GL_MODELVIEW);
  glLoadIdentity();
  glTranslatef (0.0, -4.0, -15.0);
}
void keyboard(unsigned char key, int x, int y){
  switch (key) {

  case 'g':
    gira = (gira + 5) % 360;
    glutPostRedisplay();
    break;
  case 'G':
    gira = (gira - 5) % 360;
    glutPostRedisplay();
    break;
	  case 27:           // tecla Esc (encerra o programa)
	exit(0);
	break;
  }
}

int main(int argc, char** argv){
  glutInit(&argc, argv);
  glutInitDisplayMode (GLUT_DOUBLE | GLUT_RGB);
  glutInitWindowSize (500, 500); 
  glutInitWindowPosition (100, 100);
  glutCreateWindow (argv[0]);
  init ();
  glutDisplayFunc(display); 
  glutReshapeFunc(reshape);
  glutKeyboardFunc(keyboard);
  glutMainLoop();
  return 0;
}