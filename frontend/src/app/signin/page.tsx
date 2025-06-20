"use client";
import AuthForm from "../../components/AuthForm";
import { useSignin } from "../../features/auth/hooks";

export default function SigninPage() {
  const { form, handleChange, handleSubmit, message } = useSignin();

  return (
    <main className="flex min-h-screen flex-col items-center justify-center p-24">
      <h1 className="text-2xl font-bold mb-4">Sign In</h1>
      <AuthForm
        fields={[
          {
            name: "username",
            type: "text",
            placeholder: "Username",
            value: form.username,
            onChange: handleChange,
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
        buttonLabel="Sign In"
        message={message}
        messageType={message && message.includes("success") ? "success" : message ? "error" : undefined}
      />
    </main>
  );
} 