```mermaid
flowchart TB

%% =========================
%% Frontend
%% =========================
FE[Frontend<br/>Next.js / React / Mobile]

%% =========================
%% API Layer
%% =========================
API[API Gateway<br/>Spring Boot REST]

%% =========================
%% Security
%% =========================
SEC[Security Layer<br/>JWT • RBAC • OAuth2]

%% =========================
%% Controllers
%% =========================
CTRL[Controllers<br/>REST Endpoints]

%% =========================
%% Services
%% =========================
SRV[Service Layer<br/>Business Rules]

%% =========================
%% Domain
%% =========================
DOM[Domain Layer<br/>Entities & Logic]

%% =========================
%% Repository
%% =========================
REP[Repository Layer<br/>Spring Data JPA]

%% =========================
%% Databases
%% =========================
DB[(PostgreSQL)]
CACHE[(Redis Cache)]

%% =========================
%% External / Future
%% =========================
AI[AI Validation Service- Future]
MSG[Notification / Chat WebSocket - Future]

%% =========================
%% Flow
%% =========================
FE -->|HTTPS JSON| API
API --> SEC
SEC --> CTRL
CTRL --> SRV
SRV --> DOM
DOM --> REP
REP --> DB
SRV --> CACHE

%% =========================
%% Future connections
%% =========================
SRV --> AI
SRV --> MSG
