/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
#include <stdio.h>
#include <string.h>

struct local {
    char nome[51];
    struct local *prox;
};
typedef struct local Local;

typedef struct {
    Local* topo;
} PILHA5;

typedef Local* PONT;

void pilha_cria5(PILHA5* p) {
    p = (PONT) malloc (sizeof(Local));
    p->topo = NULL;
}
void exibirPilha5(PILHA5* p) {
    PONT end = p->topo;
    printf("Pilha: \" ");
    while (end != NULL) {
        printf("%s ", end->nome);
        end = end->prox;
    }
    printf("\"\n");
}
int tamanho(PILHA5* p) {
    PONT end = p->topo;
    int tam = 0;
    while (end != NULL) {
        tam++;
        end = end->prox;
    }
    return tam;
} /* tamanho */
int pilha_vazia(PILHA5* p) {
    if (p->topo == NULL) return 1;
    return 0;
}
char pilha_pop5(PILHA5* p) {
    if (p->topo == NULL) return 0;
    char reg[51];
    strcmp(reg, p->topo->nome);
    printf("Removendo Item do topo: %s", p->topo->nome);
    PONT apagar = p->topo;
    p->topo = p->topo->prox;
    free(apagar);
    return reg;
}
void pilha_libera5(PILHA5* p) {
    PONT apagar;
    PONT posicao = p->topo;
    while (posicao != NULL) {
        apagar = posicao;
        posicao = posicao->prox;
        free(apagar);
    }
    p->topo = NULL;
}
int pilha_push5(PILHA5* p, char reg) {
    PONT novo = (PONT) malloc(sizeof (Local));
    strcmp(novo->nome, reg);
    printf("Nome elemento inserido no topo: %s", novo->nome);
    novo->prox = p->topo;
 //   p->topo = novo;
    printf("Nome elemento inserido no topo: %c", p->topo->nome);
    return 1;
}
PONT retornarTopo(PILHA5* p, char *ch) {
    if (p->topo != NULL) strcmp(*ch, p->topo->nome);
    return p->topo;
} /* retornarTopo */
int compara(Local* l1, Local* l2) {
    Local *ptr;
    PILHA5* p;
    pilha_cria5(p);
    for (ptr = l1; ptr != NULL; ptr = ptr->prox)
        pilha_push5(p, ptr->nome); //primeira lacuna
    for (ptr = l2; ptr != NULL; ptr = ptr->prox) {

        if (pilha_vazia(p)) return 0;

        if (strcmp(ptr->nome, pilha_pop5(p)) != 0) { //segunda lacuna
            pilha_libera5(p);
            return 0;
        }
    }
    if (!pilha_vazia(p))
        return 0;
    pilha_libera5(p);
    return 1;
}

void exercicio5() {
    printf("*****************Exercicio 5 ************************\n");

    PILHA5* p1;
    PILHA5* p2;
    
    pilha_cria5(p1);
    
    pilha_cria5(p2);
    
    pilha_push5(p1,"a");
  /*  pilha_push5(p1,"b");
    pilha_push5(p1,"c");
    exibirPilha5(p1);
  /*  
    pilha_push5(p2,"c");
    pilha_push5(p2,"b");
    pilha_push5(p2,"a");
    exibirPilha5(p2);
    
    if(compara(p1->topo,p2->topo)){
        printf("As pilhas são inversão");
    }else{
        printf("as pilhas não são inversas!");
    }
    */
    
    
    
}

