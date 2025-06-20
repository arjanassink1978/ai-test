"use client";
import AuthForm from "../../components/AuthForm";
import { useSignup } from "../../features/auth/hooks";

export default function SignupPage() {
  const { form, handleChange, handleSubmit, message, errorField } = useSignup();

  return (
    <main className="flex min-h-screen flex-col items-center justify-center p-24">
      <h1 className="text-2xl font-bold mb-4">Sign Up</h1>
      <AuthForm
        fields={[
          {
            name: "email",
            type: "email",
            placeholder: "Email address",
            value: form.email,
            onChange: handleChange,
            error: errorField === "email",
          },
          {
            name: "username",
            type: "text",
            placeholder: "Username",
            value: form.username,
            onChange: handleChange,
            error: errorField === "username",
          },
          {
            name: "password",
            type: "password",
            placeholder: "Password",
            value: form.password,
            onChange: handleChange,
          },
        ]}
        onSubmit={handleSubmit}
        buttonLabel="Sign Up"
        message={message}
        messageType={
          message && message.includes("exists")
            ? "error"
            : message && message.includes("success")
            ? "success"
            : undefined
        }
      />
    </main>
  );
} 