package com.example.batch.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberType {
  INDIVIDUAL((byte) 1, "Individual Member"),
  BUSINESS((byte) 2, "Business Member"),
  PREMIUM((byte) 3, "Premium Member"),
  CORPORATE((byte) 4, "Corporate Member"),
  GUEST((byte) 5, "Guest User");

  private final byte value;
  private final String description;
}
