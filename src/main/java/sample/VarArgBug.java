package sample;


import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class VarArgBug {


    public static void main(String[] args) {

        ActorSystem system = ActorSystem.create("var-arg-bug");

        // this calls:
        // def create(clazz : scala.Predef.Class[_], args : scala.AnyRef*) : akka.actor.Props
        // however IntelliJ thinks it is calling another method because it doesn't recognize var args
        Props fooProps = Props.create(Foo.class);
        system.actorOf(fooProps);


        // this also calls:
        // def create(clazz : scala.Predef.Class[_], args : scala.AnyRef*) : akka.actor.Props
        // however IntelliJ doesn't recognize the var args?
        Props barProps = Props.create(Bar.class, "bar");
        system.actorOf(barProps);
    }

    public static class Foo extends UntypedActor {
        @Override
        public void onReceive(Object message) {
            System.out.println("message = " + message);
        }
    }

    public static class Bar extends UntypedActor {

        public Bar(String name) {
        }

        @Override
        public void onReceive(Object message) {
            System.out.println("message = " + message);
        }
    }

}
