import { EnrichPerformance, Invoice, invoices, Play, Performance, Statement } from './objects';

class PerformanceCalculator {
  performance: any;
  play: any;

  constructor(aPerformance: Performance, aPlay: Play) {
    this.performance = aPerformance;
    this.play = aPlay;
  }

  get amount(): number {
    throw new Error('서브 클래스에서 처리');
  }

  get volumeCredits(): number {
    let result = 0;

    result += Math.max(this.performance.audience - 30, 0);

    if ("comedy" === this.play.type) result += Math.floor(this.performance.audience / 5);

    return result
  }
}

class TragedyCalculator extends PerformanceCalculator {
  get amount(): number {
    let result = 40000;

    if (this.performance.audience > 30) {
      result += 1000 * (this.performance.audience - 30);
    }

    return result;
  }

  get volumeCredits() {
    return Math.max(this.performance.audience - 30, 0);
  }
}

class ComedyCalculator extends PerformanceCalculator {
  get amount(): number {
    let result = 30000;

    if (this.performance.audience > 20) {
      result += 10000 + 500 * (this.performance.audience - 20);
    }

    result += 300 * this.performance.audience;

    return result;
  }

  get volumeCredits(): number {
    return super.volumeCredits + Math.floor(this.performance.audience / 5);
  }
}

function createPerformanceCalculator(aPerformance: Performance, aPlay: Play) {
  switch (aPlay.type) {
    case "tragedy": return new TragedyCalculator(aPerformance, aPlay);
    case "comedy": return new ComedyCalculator(aPerformance, aPlay);
    default: throw new Error(`알 수 없는 장르: ${ aPlay.type }`);
  }
}

export default function createStatementData(invoice: Invoice, plays: Map<string, Play>) {
  let result: Statement = new Statement();

  result.customer = invoice.customer;
  result.performances = invoice.performances.map(enrichPerformance);
  result.totalAmount = totalAmount(result);
  result.totalVolumeCredits = totalVolumeCredits(result);

  return result;

  function enrichPerformance(aPerformance: Performance): EnrichPerformance {
    const calculator = createPerformanceCalculator(aPerformance, playFor(aPerformance))

    let result: EnrichPerformance = new EnrichPerformance()

    result.playId = aPerformance.playId;

    result.play = calculator.play;
    result.amount = calculator.amount;
    result.volumeCredits = calculator.volumeCredits;

    return result;
  }

  function playFor(aPerformance: Performance): Play {
    return plays.get(aPerformance.playId)!!;
  }

  function totalAmount(data: Statement) {
    return data.performances?.reduce(
      (total: number, p: EnrichPerformance) => total + (p.amount || 0), 0
    );
  }

  function totalVolumeCredits(data: Statement) {
    return data.performances?.reduce(
      (total: number, p: EnrichPerformance) => total + (p.volumeCredits || 0), 0
    );
  }
}