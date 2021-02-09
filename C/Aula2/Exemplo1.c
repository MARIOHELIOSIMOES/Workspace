/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   Exemplo1.c
 * Author: MHS
 *
 * Created on 15 de Agosto de 2018, 17:48
 */

#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>
/*
 * 
 */
#define alturaMaxima 225
typedef struct
{
  int peso;   // peso em quilogramas
  int altura; // altura em centimetros
} PesoAltura;

int main2() {
  int x;
  PesoAltura pessoa1;
  pessoa1.peso = 80;
  pessoa1.altura = 185;

  printf("Peso: %i, Altura %i. ", pessoa1.peso, pessoa1.altura);
  if (pessoa1.altura>alturaMaxima) {
    printf("Altura acima da maxima.\n");
  }
  else printf("Altura abaixo da maxima.\n");

  printf("%p %p %p\n", &x, &pessoa1, &(pessoa1.altura));
 
  return 0;
}

