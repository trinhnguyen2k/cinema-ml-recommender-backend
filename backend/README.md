# 🎬 Cinema ML Recommender - Backend

Dự án **Cinema ML Recommender Backend** là phần **máy chủ xử lý dữ liệu và gợi ý vé xem phim rẻ nhất trong thành phố**, sử dụng **Spring Boot (Java)** và kết nối với hệ thống Frontend (**React + Vite + TypeScript**).

---

## 👩‍💻 Nhóm thực hiện

| Vai trò                | Họ và tên                    |
| ---------------------- | ---------------------------- |
| Sinh viên thực hiện    | Nguyễn Nhật Thùy Trinh       |
| Sinh viên thực hiện    | Nguyễn Hữu Duy Đạt           |
| Giảng viên hướng dẫn   | ThS. Mai Xuân Hùng           |

---

## ⚙️ Công nghệ sử dụng

- ☕ **Java 21**
- 🚀 **Spring Boot 3.x**
- 🧩 **Spring Web** – Xây dựng REST API
- 🗃️ **Spring Data JPA + Hibernate** – Làm việc với database
- 🐬 **MySQL** – Lưu trữ dữ liệu
- 🔐 **Spring Security** – Xác thực và bảo mật API
- 🧠 (Tùy chọn) **Machine Learning API Integration** – Gọi model ML để gợi ý vé xem phim

---

## 🧰 Cấu trúc thư mục
backend/
├── src/
│ ├── main/
│ │ ├── java/com/cinema/recommender/
│ │ │ ├── controller/ # Xử lý API endpoint
│ │ │ ├── service/ # Logic nghiệp vụ
│ │ │ ├── repository/ # Làm việc với database
│ │ │ ├── entity/ # Các entity (bảng dữ liệu)
│ │ │ └── Application.java # Entry point của Spring Boot
│ │ └── resources/
│ │ ├── static/ # File tĩnh (nếu có)
│ │ ├── templates/ # Giao diện (nếu dùng Thymeleaf)
│ │ └── application.properties # Cấu hình ứng dụng
│ └── test/ # Unit test (tùy chọn)
├── pom.xml # Quản lý dependency Maven
└── mvnw / mvnw.cmd # Script chạy Maven

--
🚀 Cách chạy dự án 
Cách 1: Dùng IntelliJ IDEA (đơn giản nhất)

Mở project → Click chuột phải vào Application.java → chọn Run 'Application'

Cách 2: Dùng Terminal
cd backend
mvnw spring-boot:run

---
Port mặc định: http://localhost:8080/
Truy cập http://localhost:8080/hello để test server running