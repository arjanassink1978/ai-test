"use client";
import Link from 'next/link';
import { useEffect, useState } from 'react';

export default function HomePage() {
  const [isSignedIn, setIsSignedIn] = useState(false);

  useEffect(() => {
    setIsSignedIn(!!localStorage.getItem("token"));
  }, []);

  const handleSignOut = () => {
    localStorage.removeItem("token");
    setIsSignedIn(false);
  };

  return (
    <main className="flex min-h-screen flex-col items-center justify-center p-24">
      <h1 className="text-4xl font-bold mb-4">HomePage</h1>
      {isSignedIn ? (
        <>
          <p className="mb-8">Welcome! You are signed in.</p>
          <button onClick={handleSignOut} className="px-4 py-2 bg-red-500 text-white rounded">Sign Out</button>
        </>
      ) : (
        <>
          <p className="mb-8">Welcome to the HomePage! Please sign up or sign in to continue.</p>
          <div className="flex gap-4">
            <Link href="/signup" className="px-4 py-2 bg-blue-500 text-white rounded">Sign Up</Link>
            <Link href="/signin" className="px-4 py-2 bg-green-500 text-white rounded">Sign In</Link>
          </div>
        </>
      )}
    </main>
  );
}
