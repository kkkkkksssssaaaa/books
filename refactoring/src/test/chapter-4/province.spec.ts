import {describe, expect, test, beforeEach} from "@jest/globals";
import { Province, sampleProvinceData } from "../../chapter-4/province";

describe("province", () => {
  let asia;

  beforeEach(() => {
    asia = new Province(sampleProvinceData());
  });

  test("shorfall", () => {
    expect(asia.shortfall).toBe(5);
  });

  test("profit", () => {
    expect(asia.profit).toBe(230);
  });

  test("change production", () => {
    asia.producers[0].production = 20;

    expect(asia.shortfall).toBe(-6);
    expect(asia.profit).toBe(292);
  });
});