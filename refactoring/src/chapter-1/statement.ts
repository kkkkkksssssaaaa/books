import createStatementData from "./createStatementData";
import { Invoice, Play, Statement } from "./objects";

function statement(invoice: Invoice, plays: Map<string, Play>) {
  return renderPlainText(createStatementData(invoice, plays));
}

function htmlStatement(invoice: Invoice, plays: Map<string, Play>) {
  return renderHtml(createStatementData(invoice, plays));
}

function renderPlainText(data: Statement) {
  let result = `청구 내역 (고객명: ${ data.customer })\n`;

  for (let perf of data.performances!!) {
    // 청구 내역을 출력한다
    result += ` ${ perf.play?.name }: ${ usd(perf.amount || 0) } (${ perf.audience }석\n)`
  }

  result += `총액: ${ usd(data.totalAmount || 0) }\n`
  result += `적립 포인트: ${ data.totalVolumeCredits }점\n`
  
  return result;
}

function renderHtml(data: Statement) {
  let result = `
  <h1>청구 내역 (고객명: ${ data.customer })</h1>
  <table>
    <tr>
      <th>연극</th>
      <th>좌석 수</th>
      <th>금액</th>
    </tr>
  `;

  for (let perf of data.performances!!) {
    result += `
    <tr>
      <td>
        ${ perf.play?.name }
      </td>
      <td>
        ${ perf.audience }석
      </td>
      <td>
        ${ usd(perf.amount || 0) }
      </td>
    </tr>
    `;
  }
  
  result += `</table>`;

  result += `
  <p>총액: <em>${usd(data.totalAmount || 0)}</em></p>
  <p>적립 포인트: <em>${data.totalVolumeCredits}</em>점</p>
  `
}

function usd(aNumber: number): string {
  return new Intl.NumberFormat(
    "en-US",
    {
      style: "currency",
      currency: "USD",
      minimumFractionDigits: 2
    }
  ).format(aNumber / 100);
}