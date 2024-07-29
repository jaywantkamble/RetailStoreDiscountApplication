# Retail Store Discount Calculator

## Overview

This Spring Boot application calculates the net payable amount for a bill at a retail store based on various discount rules. The application considers user types (employee, affiliate, customer) and applies corresponding discounts. Additionally, a flat discount is provided for every $100 on the bill. 

## Features

- Calculates discounts based on user type:
  - Employee: 30% discount
  - Affiliate: 10% discount
  - Customer (over 2 years): 5% discount
- Applies a flat $5 discount for every $100 on the bill.
- Ensures percentage-based discounts do not apply to groceries.
- Only one percentage-based discount applies per bill.
