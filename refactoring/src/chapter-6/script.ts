function printOwing(invoice: Invoice) {
  printBanner();

  const outstanding = calculateOutstanding(invoice);

  recordDueDate(invoice);

  printDetails(invoice, outstanding);
}

const printBanner = () => {
  console.log("*********************");
  console.log("****** 고객 채무 ******");
  console.log("*********************");
}

const printDetails = (invoice: Invoice, outstanding: number) => {
  console.log(`고객명: ${ invoice.customer }`);
  console.log(`채무액: ${ outstanding }`);
  console.log(`마감일: ${invoice.dueDate.toLocaleDateString()}`);
}

const recordDueDate = (invoice: Invoice) => {
  const today = Clock.today;
  
  invoice.dueDate = new Date(
    today.getFullYear(),
    today.getMonth(),
    today.getDate(),
    + 30
  );
}

const calculateOutstanding = (invoice: Invoice) => {
  let result = 0;

  for (const o of invoice.orders) {
    result += o.amount;
  }

  return result;
}

class Clock {
  static today = new Date();
}

interface Invoice {
  customer: string;
  dueDate: Date;
  orders: Order[];
}

interface Order {
  amount: number;
}