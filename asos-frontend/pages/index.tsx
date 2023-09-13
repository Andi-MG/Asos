import Head from 'next/head'

import styles from '@/pages/index.module.css'

export default function Home() {
  const apiUrl: string = process.env.NEXT_PUBLIC_API_URL || '';
  return (
    <div className={styles.container}>
      <Head>
        <title>Association Management</title>
        <link rel="icon" href="/favicon.ico" />
      </Head>

      <main>
        <h1 className={styles.title}>
          Association Management
        </h1>

        <p className={styles.description}>
          Work in progress
        </p>
      </main>
      <div> { apiUrl }</div>
      <footer className={styles.footer}>
        Andi Martín
      </footer>
    </div>
  )
}
