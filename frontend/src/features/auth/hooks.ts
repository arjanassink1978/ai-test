import { useState } from "react";
import { useRouter } from "next/navigation";
import { signupApi, signinApi } from "./api";
import type { SignupForm, SigninForm, AuthResponse } from "./types";

export function useSignup() {
  const [form, setForm] = useState<SignupForm>({ email: "", username: "", password: "" });
  const [message, setMessage] = useState("");
  const [errorField, setErrorField] = useState("");
  const router = useRouter();

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setMessage("");
    setErrorField("");
    try {
      const res = await signupApi(form);
      if (res.ok) {
        setMessage("Signup successful! Redirecting...");
        setTimeout(() => router.push("/signin"), 1000);
      } else {
        const text = await res.text();
        if (text.includes("Email")) setErrorField("email");
        else if (text.includes("Username")) setErrorField("username");
        setMessage(text);
      }
    } catch {
      setMessage("Signup failed. Please try again.");
    }
  };

  return { form, handleChange, handleSubmit, message, errorField };
}

export function useSignin() {
  const [form, setForm] = useState<SigninForm>({ username: "", password: "" });
  const [message, setMessage] = useState("");
  const router = useRouter();

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setMessage("");
    try {
      const res = await signinApi(form);
      if (res.ok) {
        const data: AuthResponse = await res.json();
        localStorage.setItem("token", data.token);
        setMessage("Signin successful!");
        router.push("/");
      } else {
        const text = await res.text();
        if (res.status === 401 && text === "Invalid credentials") {
          setMessage("Invalid credentials");
        } else {
          setMessage(text || "Signin failed. Please try again.");
        }
      }
    } catch {
      setMessage("Signin failed. Please try again.");
    }
  };

  return { form, handleChange, handleSubmit, message };
} 