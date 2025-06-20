import React from "react";

interface AuthFormProps {
  fields: {
    name: string;
    type: string;
    placeholder: string;
    value: string;
    onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
    error?: boolean;
  }[];
  onSubmit: (e: React.FormEvent) => void;
  buttonLabel: string;
  message?: string;
  messageType?: "success" | "error";
}

const AuthForm: React.FC<AuthFormProps> = ({ fields, onSubmit, buttonLabel, message, messageType }) => (
  <form onSubmit={onSubmit} className="flex flex-col gap-4 w-80">
    {fields.map((field) => (
      <input
        key={field.name}
        type={field.type}
        name={field.name}
        placeholder={field.placeholder}
        value={field.value}
        onChange={field.onChange}
        className={`border p-2 rounded${field.error ? " border-red-500" : ""}`}
        required
      />
    ))}
    <button type="submit" className="bg-blue-500 text-white px-4 py-2 rounded">{buttonLabel}</button>
    {message && (
      <p className={`mt-4 ${messageType === "error" ? "text-red-600" : "text-green-600"}`}>{message}</p>
    )}
  </form>
);

export default AuthForm; 