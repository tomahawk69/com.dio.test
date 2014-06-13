package com.dio.test;

/**
 * Created by yur on 21.04.2014.
 */
public class Dispatch
{
    public static void main(String args[])
    {
        A a = new A();        //объект типа A
        B b = new B();        //объект типа B
        C c = new C();        //объект типа C

        A r;                  //определить ссылку типа A

        r = a;                //r указывает на A-объект
        r.callme();           //вызывает A-версию callme

        r = b;                //r указывает на B-объект
        r.callme();           //вызывает B-версию callme

        r = c;                //r указывает на C-объект
        r.callme();           //вызывает C-версию callme

        r = (A) c;
        r.callme();           //все равно вызывает C-версию callme

    }
}

class A
{
    void callme()
    {
        System.out.println("Метод callme класса A");
    }
}

class B extends A
{
    //Переопределить callme
    void callme()
    {
        System.out.println("Метод callme класса B");
    }
}

class C extends A {
    //Переопределить callme
    void callme() {
        System.out.println("Метод callme класса С");
    }
}
