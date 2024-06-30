"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
var plays_json_1 = __importDefault(require("./plays.json"));
function statement(invoice, plays) {
    var statementData = plays_json_1.default;
    statementData.customer = invoice.customer;
    renderPlainText(statement, invoice, plays);
}
function renderPlainText(data, invoice, plays) {
    var totalAmount = 0;
    var result = "\uCCAD\uAD6C \uB0B4\uC5ED (\uACE0\uAC1D\uBA85: ".concat(invoice.customer, ")\n");
    for (var _i = 0, _a = invoice.performances; _i < _a.length; _i++) {
        var perf = _a[_i];
        // 청구 내역을 출력한다
        result += " ".concat(playFor(perf.playId).name, ": ").concat(usd(amountFor(perf)), " (").concat(perf.audience, "\uC11D\n)");
    }
    result += "\uCD1D\uC561: ".concat(usd(totalAmount), "\n");
    result += "\uC801\uB9BD \uD3EC\uC778\uD2B8: ".concat(totalVolumeCredits(), "\uC810\n");
    return result;
    function playFor(aPerformance) {
        return plays[aPerformance.playId];
    }
    function amountFor(aPerformance) {
        var result = 0;
        switch (playFor(aPerformance)) {
            case "tragedy": // 비극
                result = 40000;
                if (aPerformance.audience > 30) {
                    result += 1000 * (aPerformance.audience - 30);
                }
                break;
            case "comedy": // 희극
                result = 30000;
                if (aPerformance.audience > 20) {
                    result += 10000 + 500 * (aPerformance.audience - 20);
                }
                result += 300 * aPerformance.audience;
                break;
            default:
                throw new Error("\uC54C \uC218 \uC5C6\uB294 \uC7A5\uB974: ".concat(playFor(aPerformance).type));
        }
        return result;
    }
    function totalAmountFor() {
        var totalAmount = 0;
        for (var _i = 0, _a = invoice.performances; _i < _a.length; _i++) {
            var perf = _a[_i];
            totalAmount += amountFor(perf);
        }
        return totalAmount;
    }
    function volumeCreditsFor(aPerformance) {
        var result = 0;
        result += Math.max(aPerformance.audience - 30, 0);
        if ("comedy" === playFor(aPerformance).type)
            result += Math.floor(aPerformance.audience / 5);
        return result;
    }
    function totalVolumeCredits() {
        var volumeCredits = 0;
        for (var _i = 0, _a = invoice.performances; _i < _a.length; _i++) {
            var perf = _a[_i];
            // 포인트
            volumeCredits += volumeCreditsFor(perf);
        }
        return volumeCredits;
    }
    function usd(aNumber) {
        return new Intl.NumberFormat("en-US", {
            style: "currency",
            currency: "USD",
            minimumFractionDigits: 2
        }).format(aNumber / 100);
    }
}
