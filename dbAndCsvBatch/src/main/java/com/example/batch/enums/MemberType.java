package com.example.batch.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberType {
  INDIVIDUAL(1, "Individual Member"),
  BUSINESS(2, "Business Member"),
  PREMIUM(3, "Premium Member"),
  CORPORATE(4, "Corporate Member"),
  GUEST(5, "Guest User");

  private final int value;
  private final String description;
}
