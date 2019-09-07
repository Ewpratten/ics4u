section .data
    

section .text
global _start

_start:

    ; Set sys.exit
    ; NOTE: ebx = ret val
    mov eax, 1
    int 0x80
