"use client";
import { useState } from "react";
import { useRouter } from "next/navigation";

export default function SignupPage() {
  const [form, setForm] = useState({ email: "", username: "", password: "" });
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
      const res = await fetch("http://localhost:8082/api/auth/signup", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(form),
      });
      if (res.ok) {
        setMessage("Signup successful! Redirecting to signin page...");
        setTimeout(() => router.push("/signin"), 1000);
      } else {
        const text = await res.text();
        if (text === "Username already exists") {
          setMessage(text);
          setErrorField("username");
        } else if (text === "Email already exists") {
          setMessage(text);
          setErrorField("email");
        } else {
          setMessage(text || "Signup failed");
        }
      }
    } catch (err) {
      setMessage("Signup failed: " + err);
    }
  };

  return (
    <main className="flex min-h-screen flex-col items-center justify-center p-24">
      <h1 className="text-2xl font-bold mb-4">Sign Up</h1>
      <form onSubmit={handleSubmit} className="flex flex-col gap-4 w-80">
        <input
          type="email"
          name="email"
          placeholder="Email address"
          value={form.email}
          onChange={handleChange}
          className={`border p-2 rounded${errorField === "email" ? " border-red-500" : ""}`}
          required
        />
        <input
          type="text"
          name="username"
          placeholder="Username"
          value={form.username}
          onChange={handleChange}
          className={`border p-2 rounded${errorField === "username" ? " border-red-500" : ""}`}
          required
        />
        <input
          type="password"
          name="password"
          placeholder="Password"
          value={form.password}
          onChange={handleChange}
          className="border p-2 rounded"
          required
        />
        <button type="submit" className="bg-blue-500 text-white px-4 py-2 rounded">Sign Up</button>
      </form>
      {message && (
        <p className={`mt-4 ${message.includes("exists") ? "text-red-600" : "text-green-600"}`}>{message}</p>
      )}
    </main>
  );
} 