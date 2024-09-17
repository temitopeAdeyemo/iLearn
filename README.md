# iLearn

## Overview
This e-learning platform provides a rich online learning experience with features like video streaming, real-time chat, role-based access control, and course management. It is built using Java and Spring Boot, with various integrations for video storage, streaming protocols, and file system operations.

## Features
1. **User Authentication & Authorization (RBAC)**  
   - Login/Sign-up with email, password, first/last name.
   - Role-based access control for admin, instructors, and learners.
   - Invitation system for admin account creation.
   
2. **Course Registration**  
   - Users can browse and register for available courses.
   - Course instructors manage course content (video uploads, materials).

3. **Video Streaming (HLS/DASH)**  
   - Store videos on **AWS S3**.
   - Stream videos using **HLS** or **DASH** protocols for smooth playback.

4. **Real-time Chat (WebSocket)**  
   - Integrated WebSocket for real-time messaging between users (instructors, students).
   - Support for live interaction during course sessions.

5. **File Storage & Management**  
   - **AWS S3** integration for storing video and course materials.
   - File system operations to manage media content and user-generated files.

6. **Search Functionality (Elasticsearch)**  
   - Integrated **Elasticsearch** for fast and efficient course/content searching.
   - Full-text search capabilities to help users find relevant courses and materials.

7. **Admin Panel**  
   - Admins can manage user roles, courses, and content.
   - View and manage platform statistics and usage metrics.

8. **WebSocket Notifications**  
   - Real-time notifications for updates, chat messages, and course-related events.

9. **Security**  
   - Secure login system with JWT for session management.
   - HTTPS for encrypted communication.
   - Role-based permissions to ensure proper access control.

10. **Video Uploads & Encoding**  
    - Upload course videos to **AWS S3**.
    - Use **HLS/DASH** for adaptive bitrate streaming.
    - Handle large file uploads with progress feedback.

11. **Logging System**  
    - Integrated logging using **SLF4J** with **Logback**/**Log4j2**.
    - Structured logs stored in **Elasticsearch** for searching and analysis.
    - Log levels: DEBUG, INFO, WARN, ERROR.
    - Centralized log management with **ELK Stack (Elasticsearch, Logstash, Kibana)** or **AWS CloudWatch**.
    - Log important events like login attempts, course registrations, and system errors.

## Technologies Used
- **Backend**: Java, Spring Boot
- **Video Storage**: AWS S3
- **Video Streaming**: HLS/DASH
- **Real-time Communication**: WebSockets
- **Search**: Elasticsearch
- **Logging**: SLF4J, Logback/Log4j2, ELK Stack/AWS CloudWatch
- **Security**: Spring Security (JWT), HTTPS

## Setup and Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/temitopeAdeyemo/iLearn.git
