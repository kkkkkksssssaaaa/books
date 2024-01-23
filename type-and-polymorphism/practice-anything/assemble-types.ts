class A {
  a: number = 1;
}

interface B {
  b(): string
}

class ImpleB implements B {
  b(): string {
    throw new Error("Method not implemented.");
  }
}

class C extends A implements B {
  b(): string {
    throw new Error("Method not implemented.");
  }
  c: boolean = true;
}

function orFunction(param: A | B) {
  console.log(param);
}

function andFunction(param: A & B) {
  console.log(param);
}

function call() {
  const a: A = new A();
  const b: B = new ImpleB();
  const c: C = new C();

  orFunction(a);
  orFunction(b);
  andFunction(c);
}