export async function signupApi(form: { email: string; username: string; password: string }) {
  const res = await fetch("http://localhost:8082/api/auth/signup", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(form),
  });
  return res;
}

export async function signinApi(form: { username: string; password: string }) {
  const res = await fetch("http://localhost:8082/api/auth/signin", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(form),
  });
  return res;
} 