export interface SignupForm {
  email: string;
  username: string;
  password: string;
}

export interface SigninForm {
  username: string;
  password: string;
}

export interface AuthResponse {
  token: string;
} 