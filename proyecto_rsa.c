#include <stdio.h>
#include <stdlib.h> 
#include <gmp.h>
#define BIT_LENGTH 100

int main(int argc, char const *argv[])
{
    mpz_t n, p, q;
    gmp_randstate_t r;
    char *ptr;
    // Inicializa enteros
    mpz_init(n);
    mpz_init(p);
    mpz_init(q);
    // Inicializa random
    gmp_randinit_mt(r);
    // Genera el siguiente primo de un numero aleatorio
    // con argv[1] bits
    mpz_urandomb(p,r,BIT_LENGTH);
    mpz_nextprime(p,p);
    mpz_urandomb(q,r,BIT_LENGTH);
    mpz_nextprime(q,q);
    gmp_printf ("p = %Zd q = %Zd\n", p, q);
    mpz_clear(n);
    mpz_clear(p);
    mpz_clear(q);
    gmp_randclear(r);
    return 0;
}
