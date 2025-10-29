import React from 'react'

interface Activite {
  id: number
  nom: string
  niveau: string
  capacite: number
}

interface ActiviteCardProps {
  activite: Activite
}

export function ActiviteCard({ activite }: ActiviteCardProps) {
  const getNiveauBadgeClass = (niveau: string) => {
    switch (niveau) {
      case 'Débutant':
        return 'bg-success'
      case 'Intermédiaire':
        return 'bg-warning'
      case 'Avancé':
        return 'bg-danger'
      default:
        return 'bg-secondary'
    }
  }

  return (
    <div className="col-md-4 mb-3">
      <div className="card h-100">
        <div className="card-body">
          <h5 className="card-title">{activite.nom}</h5>
          <p className="card-text">
            <span className={`badge ${getNiveauBadgeClass(activite.niveau)} me-2`}>
              {activite.niveau}
            </span>
            <strong>Capacité:</strong> {activite.capacite} personnes
          </p>
        </div>
      </div>
    </div>
  )
}
