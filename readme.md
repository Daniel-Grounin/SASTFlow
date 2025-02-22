# 🚀 SAST Pipeline - Secure Your Code with Static Analysis

[![Build Status](https://github.com/your-username/your-repo/actions/workflows/ci.yml/badge.svg)](https://github.com/your-username/your-repo/actions/workflows/ci.yml)

## 📖 Overview
**SAST Pipeline** is a **Static Application Security Testing (SAST)** workflow that helps detect vulnerabilities in Java projects using **Maven**, **JUnit**, and **Selenium**. It integrates seamlessly with **GitHub Actions** to automate security scans and unit tests.

## ⚡ Features
- ✅ **Static Code Analysis** to detect vulnerabilities like SQL injection, XSS, and hardcoded secrets.
- ✅ **Automated Unit Testing** using **JUnit**.
- ✅ **Selenium Testing** for validating security reports.
- ✅ **GitHub Actions CI/CD** for seamless integration.

---

## 🛠️ Installation & Setup

### **Prerequisites**
Ensure you have the following installed:
- **Java 21** (Temurin JDK)
- **Maven** (`mvn` command-line tool)
- **Chrome & ChromeDriver** (for Selenium tests)

### **Clone the Repository**
```bash
git clone https://github.com/your-username/your-repo.git
cd your-repo
```

### **Run Static Analysis Manually**
```bash
mvn verify
mkdir -p reports
mv target/*.html reports/
ls -lah reports  # Debugging step
```

### **Run Unit Tests**
```bash
mvn clean test
```
### **📌 CI/CD Workflow (GitHub Actions)**
This project uses GitHub Actions for automated security scans and testing.

### Workflow Triggers
#### Push to master or main
#### Pull requests to master or main
## **CI/CD Workflow Summary**
#### ✅ Checkout Repository
#### ✅ Set up JDK 21
#### ✅ Cache Maven Dependencies
#### ✅ Run SAST Security Scan
#### ✅ Verify Report Exists
#### ✅ Build and Run Tests
#### ✅ Install Chrome & ChromeDriver

## Modify **.github/workflows/ci.yml**
To update the workflow, edit the ci.yml file in the .github/workflows/ directory.

## ScreenShot of the analysis
![Screenshot_4](https://github.com/user-attachments/assets/14522f42-9fd0-4bcb-8d5c-480dbcb71e02)


